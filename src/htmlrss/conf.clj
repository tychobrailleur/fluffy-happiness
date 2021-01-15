(ns htmlrss.conf
  (:require [clojure.edn :as edn]))

(defn read-config []
  (edn/read-string (slurp "resources/config.edn")))
