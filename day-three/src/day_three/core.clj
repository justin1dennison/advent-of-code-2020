(ns day-three.core
  (:require [clojure.string :as str])
  (:gen-class))

(def input-filepath "./resources/input.txt")
(def grid
  (->>
   input-filepath
   slurp
   str/split-lines))
(def slopes [[1 1] [3 1] [5 1] [7 1] [1 2]])
(def multiply (partial apply *))
(defn zip [xs ys] (map vector xs ys))

(defn solve [grid [dx dy]]
  (let [y-limit (count grid)
        x-limit (count (get grid 0))
        ys (iterate #(mod (+ dy %) y-limit) dy)
        xs (iterate #(mod (+ dx %) x-limit) dx)
        n (dec (/ y-limit dy))
        counts (->> (zip ys xs) (take n) (map (partial get-in grid)) frequencies)] (get counts \#)))

(defn -main
  [& args]
  (println {:part-one (solve grid [3 1])
            :part-two (->> slopes (map (partial solve grid)) multiply)}))

;; playground
(def example-grid (->> "./resources/example.txt" slurp str/split-lines))

;;example with slopes
;; (2, 7, 3, 4, 2)
(map (partial solve example-grid) slopes)
;;solution
;;61
;;257
;;64
;;47
;;37
;;1744787392