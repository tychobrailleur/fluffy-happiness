(ns htmlrss.server
  (:require [htmlrss.client :as client]
            [clojure.data.xml :refer :all]
            [htmlrss.utils :as utils]
            [ring.util.request :as req])
  (:use ring.adapter.jetty))


(defn entry->xml [entry url]
  (element :item {}
           (element :title {} (entry :title))
           (element :link {} (str url (entry :link)))
           (element :pubDate {} (utils/convert-date-format
                                 "yyyy MMM dd"
                                 "E, dd MMM yyyy 08:00:00 'GMT'"
                                 (entry :date)))
           (element :guid {} (str url (entry :link)))))


(defn create-xml-response [items conf]
  (let [xml-items (map #(entry->xml % (conf :url)) items)]
    (emit-str
     (element :rss {:version "2.0"}
              (element :channel {}
                       (element :title {} (conf :title))
                       (element :link {} (conf :url))
                       (element :language {} (conf :lang))
                       xml-items)))))


(defn handler [request conf]
  (let [path (subs (req/path-info request) 1)
        feed (get (conf :feeds) (keyword path))]
    (if feed
      (let [content (client/fetch-html (feed :url))
            items (client/post-titles content feed)]
        {:status 200
         :headers {"Content-Type" "application/rss+xml"}
         :body (create-xml-response items feed)})
      (if (= path "/")
        {:status 200
         :headers {"Content-Type" "text/html"}
         :body "<html>ok</html>"}))))

(defn start-server [conf]
  (println "Starting server on port" (conf :port) "...")
  (run-jetty (fn [req] (handler req conf))
             {:port (conf :port)}))
