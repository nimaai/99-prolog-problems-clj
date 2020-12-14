(ns ninety-nine-problems.lists.p21
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne insert-ato [x l n r]
    ([x xs 1 [x . xs]])
    ([x [y . ys] n [y . zs]] (l/fresh [n1]
                               (la/> n 1)
                               (l/is n1 n dec)
                               (insert-ato x ys n1 zs))))

  (t/is (= (l/run* [x r] (remove-ato x, '[a,b,c,d], 2, r))
           '([b [a,c,d]]))))

(comment (t/run-tests))
