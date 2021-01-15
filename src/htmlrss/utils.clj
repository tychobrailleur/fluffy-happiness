(ns htmlrss.utils)


(defn convert-date-format [from to date]
  (.format
   (java.text.SimpleDateFormat. to)
   (.parse
    (java.text.SimpleDateFormat. from)
    date)))
