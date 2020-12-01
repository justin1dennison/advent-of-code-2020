(ns day-six.core
  (:require [clojure.string :as str]
            [clojure.set :as set])
  (:gen-class))

(def input-filepath "./resources/input.txt")
(defn- split [pattern s]
  (str/split s pattern))

(defn count-group [combinator group]
  (->> group
       str/split-lines
       (map set)
       (apply combinator)
       count))

(defn solve [combinator input]
  (->> input
       (split #"\n\n")
       (map (partial count-group combinator))
       (apply +)))

(defn -main [& args]
  (let [input (slurp input-filepath)]
    (println {:part-one (solve set/union input)
              :part-two (solve set/intersection input)})))
