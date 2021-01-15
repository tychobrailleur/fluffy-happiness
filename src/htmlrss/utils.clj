(ns htmlrss.utils)


(defn convert-date-format [from to date]
  (try (.format
   (java.text.SimpleDateFormat. to)
   (.parse
    (java.text.SimpleDateFormat. from)
    date))
  (catch Exception e (str from " -> " to " (" date "): " (.getMessage e)))))
