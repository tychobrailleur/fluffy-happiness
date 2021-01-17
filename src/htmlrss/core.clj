(ns htmlrss.core
  (:require [htmlrss.client :as client]
            [htmlrss.conf :as conf]
            [htmlrss.server :as server])
  (:gen-class))


(defn -main [& args]
  (-> (conf/read-config)
      server/start-server))
