(ns day-two.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn to-int [s] (Integer/parseInt s))

(def input-filepath "resources/input.txt")

(defn parse-policy [policy-txt]
  (let [[rng letter] (str/split policy-txt #" ")
        [lower upper] (->> (str/split rng #"-") (map to-int))]
    {:rng [lower upper] :letter (first letter)}))

(defn parse-line [line]
  (let [[policy password] (str/split line #":")]
    {:policy (parse-policy policy) :password password}))

(defn get-input [filepath]
  (->> filepath
       slurp
       (str/split-lines)
       (map parse-line)))

(defn valid? [{:keys [policy password]}]
  (let [{:keys [rng letter]} policy
        [lower upper] rng
        letter-freq (frequencies password)
        cnt (get letter-freq letter 0)]
    (and (<= cnt upper) (>= cnt lower))))

(defn solve [] (->> (get-input input-filepath) (filter valid?) count))

(defn -main
  [& args]
  (println {:result (solve)}))
