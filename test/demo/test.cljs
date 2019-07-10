(ns demo.test
  (:require-macros [cljs.test :refer [async]])
  (:require [cljs.test :refer [deftest is] :refer-macros [use-fixtures]]))

(use-fixtures :once
  {:before (fn []
             (async done
                    (js/setTimeout #(do
                                      (js/console.log ":once :before 1")
                                      (done))
                                   2000)))
   :after  (fn []
             (js/console.log ":once :after"))}

  {:before (fn []
             (async done
                    (js/setTimeout #(do
                                      (js/console.log ":once :before 2")
                                      (done))
                                   2000)))
   :after  (fn []
             (js/console.log ":once :after"))})

(use-fixtures :each
  {:before (fn []
             (js/console.log ":each :before"))
   :after  (fn []
             (js/console.log ":each :after"))})

(deftest example-with-timeout
  (js/console.log "start of test")
  (async done
         (js/setTimeout (fn []
                          (is (= 1 2))
                          (js/console.log "end of async test")
                          (done))
                        5000)))

(deftest example-without-timeout
  (js/console.log "in regular test")
  (is (= 3 2))
  )
