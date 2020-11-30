(ns day-five.core
  (:require [clojure.string :as str])
  (:gen-class))

(def input-filepath "./resources/input.txt")
(def round-up (comp int #(Math/ceil %)))
(def round-down (comp int #(Math/floor %)))
(defn average [xs]
  (/ (apply + xs) (count xs)))
(defn tick [args]
  (let [{:keys [steps bounds]} args]
    (case (first steps)
      (\F \L) {:steps (rest steps) :bounds [(first bounds) (round-down (average bounds))]}
      (\B \R) {:steps (rest steps) :bounds [(round-up (average bounds)) (second bounds)]}
      (first bounds))))
(defn seat-id [row col] (+ col (* row 8)))
(defn solve [instructions]
  (let [row-instructions  (take 7 instructions)
        row-bounds [0 127]
        initial-row {:steps row-instructions :bounds row-bounds}
        row-number (->> initial-row (iterate tick) (take-while (complement nil?)) last)
        col-instructions (take-last 3 instructions)
        col-bound [0 7]
        initial-col {:steps col-instructions :bounds [0 7]}
        col-number (->> initial-col (iterate tick) (take-while (complement nil?)) last)]
    {:row row-number :column col-number :seat-id (seat-id row-number col-number)}))

(defn -main [& args]
  (println {:part-one (->> input-filepath slurp str/split-lines (map solve) (map :seat-id) (apply max))}))


