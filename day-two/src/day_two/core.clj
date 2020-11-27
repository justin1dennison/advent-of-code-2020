(ns day-two.core
  (:require [day-two.part-one :as one]
            [day-two.part-two :as two])
  (:gen-class))

(def input-filepath "resources/input.txt")

(defn -main
  [& args]
  (println {:part-one (one/solve input-filepath) :part-two (two/solve input-filepath)}))
