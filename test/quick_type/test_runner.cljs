(ns quick-type.test-runner
  (:require [quick-type.core-test]
            [figwheel.main.testing :refer [run-tests-async]]))


(enable-console-print!)

(defn -main [& args]
  (run-tests-async 5000))

