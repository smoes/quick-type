(defproject quick-type "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.238" :scope "provided"]
                 [de.active-group/active-clojure "0.30.0"]
                 ]

  :aliases {"fig:test" ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" quick-type.test-runner]}
  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main "0.1.9"]]}})
