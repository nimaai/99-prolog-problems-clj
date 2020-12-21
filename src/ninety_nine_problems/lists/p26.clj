(ns ninety-nine-problems.lists.p26
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(l/defne elo [_x _l _r]
  ([x [x . l] l])
  ([x [_ . l] r] (elo x l r)))

(t/with-test
  (l/defne combinationo
    "c is a list of k distinct elements chosen from the list l"
    [_k _l _c]
    ([0 _ []])
    ([k l [x . xs]] (fresh [k1 r]
                      (la/> k 0)
                      (elo x l r)
                      (l/is k1 k dec)
                      (combinationo k1 r xs))))

  (t/is (= (l/run* [l] (combinationo 2 '[a b c] l))
           '[[a b] [a c] [b c]])))

(comment (t/run-tests))
