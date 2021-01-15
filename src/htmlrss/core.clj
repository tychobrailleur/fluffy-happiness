(ns htmlrss.core
  (:require [htmlrss.client :as client]
            [htmlrss.conf :as conf]
            [htmlrss.server :as server]))


(defn -main [& args]
  (let [conf (conf/read-config)]
    (server/start-server conf)
    (let [content (client/fetch-html "https://vitalik.ca")]
      (client/post-titles content {}))))
