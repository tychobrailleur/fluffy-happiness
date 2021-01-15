(defproject htmlrss "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "GNU General Public License v3.0"
            :url "none"
            :year 2021
            :key "gpl-3.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [javax.servlet/servlet-api "2.5"]
                 [org.clojure/data.xml "0.0.8"]
                 [clj-http "3.10.3"]
                 [hickory "0.7.1"]
                 [ring "1.8.2"]]
  :repl-options {:init-ns htmlrss.core}
  :main htmlrss.core)
