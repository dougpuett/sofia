(defproject sofia "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring "1.3.1"]
                 [cheshire "5.3.1"]
                 [compojure "1.2.0"]]
  :plugins [[lein-ring "0.8.11"]]
  :main ^:skip-aot sofia.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})