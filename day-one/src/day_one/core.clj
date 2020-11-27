(ns day-one.core
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as c])
  (:gen-class))

(defn parse-int [s] (Integer/parseInt s))

(def input
  (->> "resources/input.txt"
       slurp
       str/split-lines
       (map parse-int)))

(def sum (partial apply +))

(defn -main []
  (let [combos (c/combinations input 2)
        selected (first (filter #(= 2020 (sum %)) combos))]
    (println {:result (apply * selected)})))
