(ns ninety-nine-problems.lists.p25
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]
            [clojure.set :refer [subset?]]
            [ninety-nine-problems.lists.p04 :refer [lengtho]]
            [ninety-nine-problems.lists.p23 :refer [rnd-selecto]]))

(t/with-test
  (defn rand-permuo
    "The list l2 is a random permutation of the
     elements of the list l1."
    [l1 l2]
    (l/fresh [l]
      (lengtho l1 l)
      (rnd-selecto l1 l l2)))

  (t/is (let [input '[a,b,c,d,e,f,g,h]]
          (-> (l/run* [l] (rand-permuo input l))
              first
              set
              (= (set input))))))

(comment (t/run-tests))
