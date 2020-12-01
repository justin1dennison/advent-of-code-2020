(ns day-six.core-test
  (:require [midje.sweet :refer :all]
            [day-six.core :refer :all]
            [clojure.set :as set]))

(facts "about `count-group`"
       (fact "with set/union finds one person answered questions"
             (count-group set/union "abc") => 3
             (count-group set/union "a\nb\nc") => 3
             (count-group set/union "ab\nac") => 3
             (count-group set/union "a\na\na\na") => 1
             (count-group set/union "b") => 1)
       (fact "with set/intersection finds all personed answered questions"
             (count-group set/intersection "abc") => 3
             (count-group set/intersection "a\nb\nc") => 0
             (count-group set/intersection "ab\nac") => 1
             (count-group set/intersection "a\na\na\na") => 1
             (count-group set/intersection "b") => 1))





