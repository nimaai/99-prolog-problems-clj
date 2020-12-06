(ns ninety-nine-problems.lists.p20
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne remove-ato
    "x is the k'th element of the list l; r is the list
    that remains when the k'th element is removed from l."
    [x l k r]
    ([x [x . xs] 1 xs])
    ([x [y . ys] n [y . zs]] (l/fresh [n1]
                               (la/> n 1)
                               (l/is n1 n dec)
                               (remove-ato x ys n1 zs))))

  (t/is (= (l/run* [x r] (remove-ato x, '[a,b,c,d], 2, r))
           '([b [a,c,d]]))))

(comment (t/run-tests))
