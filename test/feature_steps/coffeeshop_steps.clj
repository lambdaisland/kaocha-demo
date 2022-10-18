(ns feature-steps.coffeeshop-steps
  (:require [lambdaisland.cucumber.dsl :refer :all]
            [clojure.test :refer :all]))


(Given "the following price list" [state table]
       (assoc state
              :price-list
              (into {}
                    (map (fn [[k v]]
                           [k (Double/parseDouble v)]))
                    table)))

(When "I order a (.*)" [state product]
  (update state :order conj product))

(And "pay with ${double}" [{:keys [price-list order] :as state} paid]
  (doseq [product order]
    (is (contains? price-list product)))

  (let [total (apply + (map price-list order))]
    (is (<= total paid))

    (assoc state
           :total total
           :paid paid
           :change (- paid total))))

(Then "I get ${double} back" [{:keys [change] :as state} expected]
      (is (= expected change))
      state)

(Before [] (println "\nIn before hook"))
(After [] (println "\nIn after hook"))
