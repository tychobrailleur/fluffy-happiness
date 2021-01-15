(ns htmlrss.client
  (:require [clj-http.client :as client]
            [hickory.select :as s]
            [hickory.core :as hic]
            [clojure.string :as str]))


(defn fetch-html [url]
  (-> (client/get url)
      :body
      hic/parse
      hic/as-hickory))


(defn extract-meta [element]
  (let [date (->> element
                  (s/select (s/child (s/class "post-meta")))
                  first
                  :content
                  first)
        link-element (->> element
                          (s/select (s/child (s/class "post-link")))
                          first)
        link (->> link-element
                  :attrs
                  :href
                  str/trim)
        title (->> link-element
                   :content
                   first
                   str/trim)]
    {:title title :date date :link link}))


(defn post-titles [content conf]
  (->> content
       (s/select (s/child
                  (s/class "post-list")
                  (s/tag :li)))
       (map extract-meta)))
