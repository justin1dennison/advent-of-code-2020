(ns day-two.lib
  (:require [clojure.string :as str]))

(defn to-int [s] (Integer/parseInt s))

(defn parse-policy [policy-txt]
  (let [[rng letter] (str/split policy-txt #" ")
        [lower upper] (->> (str/split rng #"-") (map to-int))]
    {:rng [lower upper] :letter (first letter)}))

(defn parse-line [line]
  (let [[policy password] (str/split line #": ")]
    {:policy (parse-policy policy) :password password}))

(defn ingest [filepath]
  (->> filepath
       slurp
       (str/split-lines)
       (map parse-line)))
