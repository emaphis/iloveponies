(ns i-am-a-horse-in-the-land-of-booleans
  (:refer-clojure :exclude [boolean]))

;;(use 'clojure.repl)


;; Exercise 1 
;; Implement `(boolean x)`, which works like the built-in `boolean` function:
;; for `nil` and `false`, it returns `false`, and for all other values it
;; returns `true`.
;; You can use `if` in its implementation, but not the build-in boolean.

(defn boolean [x]
  (if x
    true
    false))

(boolean "foo") ;; => true
(boolean nil) ;; => false
(boolean (+ 2 3)) ;; => true
(boolean true) ;; => true
(boolean false) ;; => false


;;; Comparing values
;; values can be compared for equality with `=`

(= "foo" "foo") ;; => true
(= "foo" "bar") ;; => false

;; compare numerical values with `==`
(== 42 42) ;; => true
(== 5.0 5) ;; => true
(= 5.0 5) ;; => false

;; Exercise 2 
;; Write the function `(teen? age)`, which returns truthy if `age` is at least
;; 13 and at most 19.
;; Use only one comparison operator and give it three arguments.

(defn teen? [age]
  (< 12 age 20))

(teen? 12);; => false
(teen? 13) ;; => true
(teen? 15) ;; => true
(teen? 19) ;; => true
(teen? 20) ;; => false


;;; Everything has a value

(defn sign [x]
  (if (< x 0)
    "-"
    "+"))
;; the `if` expression returns a value

(sign 2) ;; => "+"
(sign -2) ;; => "-"
(sign 0) ;; => "+"


;;; Conditional evaluation


;;  Exercise 3

;; Write the function `(abs n)`, which returns the absolute value of
;; n, i.e. if `(n < 0)`, the value of `(abs n)` is `(- n)`, and
;; otherwise `(n)`.

(defn abs [x]
  (if (< x 0)
    (- x)
    x))

(abs -2) ;; => 2
(abs 42) ;; => 42


;;  Exercise 4

;; Write the function `(divides? divisor n)`, which returns `true` if
;; `divisor` divides `n` and `false` otherwise.

(mod 10 5) ;; => 0  - divides evenly
(mod 10 3) ;; => 1

(defn divides? [divisor n]
  (== (mod n divisor) 0))

(divides? 2 4) ;; => true
(divides? 4 2) ;; => false
(divides? 5 10) ;; => true
(divides? 2 5) ;; => false


;;; Cond expression

(defn super-sign [number]
  (cond
    (neg? number) "negative"
    (pos? number) "positive"
    :else         "zero"))

(super-sign 13) ;; => "positive"
(super-sign 0) ;; => "zero"
(super-sign -5) ;; => "negative"


;;  Exercise 5

;; Write the function `(fizzbuzz n)` that returns

;; - "fizz" when n is `divisible` by 3,
;; - "buzz" when n is `divisible` by 5,
;; - "gotcha!" when n is `divisible` by 15, and
;; - the empty string "" otherwise.

;; Use the `divides?` function from the previous exercise.

(defn fizzbuzz [n]
  (cond
    (divides? 15 n) "gotcha!"
    (divides? 3 n) "fizz"
    (divides? 5 n) "buzz"
    :else  ""))

(fizzbuzz 2) ;; => ""
(fizzbuzz 48) ;; => "fizz"
(fizzbuzz 45) ;; => "gotcha!"
(fizzbuzz 70) ;; => "buzz"


;;  Exercise 6

;; Write a function (generic-doublificate x) that takes one argument and

;; - doubles it if it is a number,
;; - returns nil if it is an empty collection,
;; - if it is a list or a vector, returns two times the length of it
;; - returns true otherwise.

;; You can use the following functions:

;; - (number? n) returns true if n is a number.
;; - (empty? coll) returns true if coll is empty.
;; - (list? coll) and (vector? coll) test if coll is a list or a vector.
;; - (count coll) returns the length of a list or a vector.

(defn generic-doublificate [x]
  (cond
    (number? x) (* 2 x)
    (empty? x) nil
    (or (list? x) (vector? x)) (* 2 (count x))
    :else true))

(generic-doublificate 1) ;; => 2
(generic-doublificate [1 2]) ;; => 4
(generic-doublificate '(62 21)) ;; => 4
(generic-doublificate {}) ;; => nil
(generic-doublificate []) ;; => nil
(generic-doublificate {:a 1}) ;; => true


;;; Boolean Functions

;;  Exercise 7

;; Write the function (not-teen? age), which returns true when teen?
;; returns false and false otherwise.

(defn not-teen? [age]
  (not (teen? age)))

(not-teen? 13) ;; => false
(not-teen? 25) ;; => true
(not-teen? 12) ;; => true
(not-teen? 29) ;; => true



(defn leap-year? [year]
  (cond (== 0 (mod year 400)) true
        (== 0 (mod year 100)) false
        (== 0 (mod year 4)) true
        :else false))

(leap-year? 100) ;; => false
(leap-year? 200) ;; => false
(leap-year? 400) ;; => true
(leap-year? 12)  ;; => true
(leap-year? 20) ;; => true
(leap-year? 15) ;; => false


;; '_______'
