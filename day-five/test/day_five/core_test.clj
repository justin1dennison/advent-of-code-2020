(ns day-five.core-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-five.core :refer :all]))

(facts "about `solve`"
       (solve "BFFFBBFRRR") => {:row 70 :column 7 :seat-id 567}
       (solve "FFFBBBFRRR") => {:row 14 :column 7 :seat-id 119}
       (solve "BBFFBBFRLL") => {:row 102 :column 4 :seat-id 820})
