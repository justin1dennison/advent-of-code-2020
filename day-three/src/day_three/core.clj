(ns day-three.core
  (:require [clojure.string :as str])
  (:gen-class))

(def input-filepath "./resources/input.txt")
(def grid
  (->>
   input-filepath
   slurp
   str/split-lines))

(defn zip [xs ys] (map vector xs ys))
(defn get-point [[idx row]] (nth (cycle row) idx))

(defn solve [grid [dx dy]]
  (let [nrows (count grid)
        ys  (range nrows)
        xs  (map #(dec (+ (* dx %) dy)) ys)
        points (->> (zip xs grid) (map get-point))]
    (-> (frequencies points) (get \#))))

(defn -main
  [& args]
  (println {:part-one (solve grid [3 1])}))
