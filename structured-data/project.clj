(defproject structured-data "1.0.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [midje-grader "0.1.0-SNAPSHOT"]]
  :profiles {:dev {:dependencies [[midje "1.9.8"]]
                   :plugins [[lein-midje "3.2.1"]]}})

