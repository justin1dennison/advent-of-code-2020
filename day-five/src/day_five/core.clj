(ns day-five.core
  (:require [clojure.string :as str]
            [clojure.set :as set])
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
(defn info [instructions]
  (let [row-instructions  (take 7 instructions)
        row-bounds [0 127]
        initial-row {:steps row-instructions :bounds row-bounds}
        row-number (->> initial-row (iterate tick) (take-while (complement nil?)) last)
        col-instructions (take-last 3 instructions)
        col-bound [0 7]
        initial-col {:steps col-instructions :bounds [0 7]}
        col-number (->> initial-col (iterate tick) (take-while (complement nil?)) last)]
    {:row row-number :column col-number :seat-id (seat-id row-number col-number)}))

(def possible-columns #{0 1 2 3 4 5 6 7})
(defn -main [& args]
  (let [input (->> input-filepath slurp str/split-lines)
        part-one (->> input
                      (map (comp :seat-id info))
                      (apply max))
        part-two (->> input
                      (map (comp (juxt :row :column) info))
                      (group-by first)
                      (map (fn [[k v]] [k (map second v)]))
                      (filter (comp (partial > 8) count second))
                      (sort-by first)
                      first
                      (apply (fn [row columns] [row (first (set/difference possible-columns columns))]))
                      (apply seat-id))]
    (println {:part-one part-one :part-two part-two})))



