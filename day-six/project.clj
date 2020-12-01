(defproject day-six "0.0.1-SNAPSHOT"
  :description "Cool new project to do things and stuff"
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot day-six.core
  :target-path "target/%s"
  :profiles {:dev {:dependencies [[midje "1.9.9"]]}
             :midje {}})
             ;; Note that Midje itself is in the `dev` profile to support
             ;; running autotest in the repl.

  
