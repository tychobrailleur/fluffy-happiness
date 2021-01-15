(ns htmlrss.core
  (:require [htmlrss.client :as client]
            [htmlrss.conf :as conf]
            [htmlrss.server :as server]))


(defn -main [& args]
  (-> (conf/read-config)
      server/start-server))
