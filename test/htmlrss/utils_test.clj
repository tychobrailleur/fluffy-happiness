(ns htmlrss.utils-test
  (:require [htmlrss.utils :as sut]
            [clojure.test :refer :all]))


(deftest test-convert
  (testing "Convert a date in a format to another format"
    (is (= "Fri, 15 Jan 2021 08:00:00 GMT" (sut/convert-date-format "yyyy MMM dd" "E, dd MMM yyyy 08:00:00 'GMT'" "2021 Jan 15")))))
