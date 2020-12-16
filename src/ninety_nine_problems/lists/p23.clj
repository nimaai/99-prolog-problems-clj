(ns ninety-nine-problems.lists.p23
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]
            [clojure.set :refer [subset?]]
            [ninety-nine-problems.lists.p04 :refer [lengtho]]
            [ninety-nine-problems.lists.p20 :refer [remove-ato]]))

(t/with-test
  (l/defne rnd-selecto
    "The list r contains n randomly selected 
     items taken from the list l."
    [l n r]
    ([_ 0 []])
    ([xs n [x . zs]] (l/fresh [l i ys n1]
                       (la/> n 0)
                       (lengtho xs l)
                       (l/is i l #(-> % rand-int (+ 1)))
                       (remove-ato x xs i ys)
                       (l/is n1 n dec)
                       (rnd-selecto ys n1 zs))))

  (t/is (let [input '[a,b,c,d,e,f,g,h]]
          (-> (l/run* [l] (rnd-selecto input 3 l))
              first
              ((fn [result]
                 (and (= (count result) 3)
                      (subset? (set result)
                               (set input)))))))))

(comment (t/run-tests))
