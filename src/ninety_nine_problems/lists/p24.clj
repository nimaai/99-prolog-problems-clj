(ns ninety-nine-problems.lists.p24
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]
            [clojure.set :refer [subset?]]
            [ninety-nine-problems.lists.p22 :refer [rangeo]]
            [ninety-nine-problems.lists.p23 :refer [rnd-selecto]]))

(t/with-test
  (defn lottoo
    "The list l contains n randomly selected distinct
     integer numbers from the interval 1..m"
    [n m l]
    (l/fresh [r]
      (rangeo 1 m r)
      (rnd-selecto r n l)))

  (t/is (let [input '[a,b,c,d,e,f,g,h]]
          (-> (l/run* [l] (rnd-selecto input 3 l))
              first
              ((fn [result]
                 (and (= (count result) 3)
                      (subset? (set result)
                               (set input)))))))))

(comment (t/run-tests))
