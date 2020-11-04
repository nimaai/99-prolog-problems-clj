(ns ninety-nine-problems.core
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]))

(comment
  (run* [q]
    (== q true)))
