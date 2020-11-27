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

(defn solver [n]
  (let [combos (c/combinations input n)
        selected (first (filter #(= 2020 (sum %)) combos))]
    (apply * selected)))

(defn -main []
  (println {:result-part-one (solver 2) :result-part-two (solver 3)}))
