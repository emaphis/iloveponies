(ns structured-data)

;;(use 'clojure.repl)


;;; `Let` expression - local names
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(let [x 42]
  (+ x x))
;; => 84

(defn hypotenuse [x y]
  (let [xx (* x x)
        yy (* y y)]
    (Math/sqrt (+ xx yy))))

(hypotenuse 3 4) ;; => 5.0


;;  Exercise 1

;; The following function does a thing:

(defn do-a-thing' [x]
  (Math/pow (+ x x) (+ x x)))

(do-a-thing' 3) ;; => 46656.0

;; Change the function `do-a-thing` so that it uses `let` to give a
;; name to the common expression `(+ x x)` in its body. 

(defn do-a-thing [x]
  (let [xx (+ x x)]
    (Math/pow xx xx)))

(do-a-thing 3)  ;; => 46656.0
(Math/pow 6 6)  ;; => 46656.0


;; can refer to previous names
(let [a 10
      b (+ a 8)]
  (+ a b))
;; => 28


;;; Vectors
;;;;;;;;;;;;;;;;

(get ["a" "b" "c"] 1) ;; => "b"
(get ["a" "b" "c"] 15) ;; => nil
(get ["x"] 0) ;; => "x"


;;  Exercise 2

;; Write the function `(spiff v)` that takes a `vector` and returns the
;; sum of the first and third elements of the vector.
;; What happens when you pass in a vector that is too short?

;; (+ 3 nil) => exception

(defn spiff [v]
  (+ (get v 0)
     (get v 2)))

(spiff [1 2 3]) ;; => 4
(spiff [1 2 3 4 5 6]) ;; => 4
;; (spiff [1 2]) => exception
;; (spiff [])  => exception


;;; Basic vector operations
;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(conj [1 2 3] 4)  ;; => [1 2 3 4]
(assoc [1 2 3 4] 2 "foo") ;; => [1 2 "foo" 4]


;;  Exercise 3

;; Write the function `(cutify v)` that takes a vector as a parameter and
;; adds "<3" to its end.

(defn cutify [v]
  (conj v "<3"))

(cutify []) ;; => ["<3"]
(cutify [1 2 3]) ;; => [1 2 3 "<3"]
(cutify ["a" "b"]) ;; => ["a" "b" "<3"]


;;; Vector deconstruction
;;;;;;;;;;;;;;;;;;;;;;;;;;

(let [[x y z] [1 2 3 4 5 6]]
  (str x y z))
;; => "123"


;; Exercise 4

;; Rewrite our earlier function spiff by destructuring its parameter.
;; Call this new function spiff-destructuring

(defn spiff-destructuring [v]
  (let [[x _ z] v]
    (+ x z)))

(spiff-destructuring [1 2 3]) ;; => 4
(spiff-destructuring [1 2 3 4 5 6]) ;; => 4
;; (spiff-destructuring [1 2]) => exception
;; (spiff-destructuring [])  => exception


;; destructuring parameters

(defn sum-pairs' [first-pair second-pair]
  [(+ (first first-pair) (first second-pair))
   (+ (second first-pair) (second second-pair))])

(sum-pairs' [42 5] [-42 -5]) ;; => [0 0]
(sum-pairs' [64 256] [-51 -219]) ;; => [13 37]

;; let's destructure

(defn sum-pairs [[x1 y1] [x2 y2]]
  [(+ x1 x2) (+ y1 y2)])

(sum-pairs [42 5] [-42 -5]) ;; => [0 0]
(sum-pairs [64 256] [-51 -219]) ;; => [13 37]


;;; Boxes
;;;;;;;;;;

(defn point [x y]
  [x y])


(defn rectangle [bottom-left top-right]
  [bottom-left top-right])


;;  Exercise 5

;; Write the functions (height rectangle) and (width rectangle) that
;; return the height and width of the given rectangle.
;; Use destructuring.

(defn height [rectangle]
  (let [[[_ y1] [_ y2]] rectangle]
    (- y2 y1)))

(height (rectangle [1 1] [5 1])) ;; => 0
(height (rectangle [1 1] [5 5])) ;; => 4
(height (rectangle [0 0] [2 3])) ;; => 3


(defn width [rectangle]
  (let [[[x1 _] [x2 _]] rectangle]
    (- x2 x1)))

(width (rectangle [1 1] [5 1])) ;; => 4
(width (rectangle [1 1] [1 1])) ;; => 0
(width (rectangle [3 1] [10 4])) ;; => 7


;;  Exercise 6

;; Write the function (square? rectangle) that returns true if
;; rectangle is a square and otherwise false.

(defn square? [rectangle]
  (== (width rectangle)
      (height rectangle)))

(square? (rectangle [1 1] [2 2])) ;=> true
(square? (rectangle [1 1] [2 3])) ;=> false
(square? (rectangle [1 1] [1 1])) ;=> true
(square? (rectangle [3 2] [1 0])) ;=> true
(square? (rectangle [3 2] [1 1])) ;=> false


;;  Exercise 7

;; Write the function (area rectangle) that returns the area of the
;; given rectangle.

(defn area [rectangle]
  (* (width rectangle)
     (height rectangle)))

(area (rectangle [1 1] [5 1])) ;; => 0
(area (rectangle [0 0] [1 1])) ;; => 1
(area (rectangle [0 0] [4 3])) ;; => 12
(area (rectangle [3 1] [10 4]));; => 21


;;  Exercise 8

;; Write the function `(contains-point? rectangle point)` that returns `true` if
;; `rectangle` contains `point` and otherwise `false`.

;; Remember that you can give `<=` multiple parameters. `(<= x y z`) returns `true`
;; if  /() x \leq y \leq z\) holds. Otherwise `false`.

;; Hint: `and` is useful.

(defn contains-point? [rectangle point]
  (let [[[x1 y1] [x2 y2]] rectangle
        [x y] point]
    (and (<= x1 x x2)
         (<= y1 y y2))))

(contains-point? (rectangle [0 0] [2 2])
                 (point 1 1))            ;=> true
(contains-point? (rectangle [0 0] [2 2])
                 (point 2 1))            ;=> true
(contains-point? (rectangle [0 0] [2 2])
                 (point -3 1))           ;=> false
(contains-point? (rectangle [0 0] [2 2])
                 (point 1 3))            ;=> false
(contains-point? (rectangle [1 1] [2 2])
                 (point 1 1))            ;=> true
(contains-point? (rectangle [1 1] [1 1])
                 (point 1 1))            ;=> true


;;  Exercise 9

;; Write the function (contains-rectangle? outer inner) that returns `true` if the
;; `rectangle` inner is inside the rectangle outer and otherwise `false`.

;; Hint: use contains-point?

(defn contains-rectangle? [outer inner]
  (let [[pt1 pt2] inner]
    (and (contains-point? outer pt1)
         (contains-point? outer pt2))))

(contains-rectangle? (rectangle [0 0] [3 3])
                     (rectangle [1 1] [2 2])) ;=> true
(contains-rectangle? (rectangle [0 0] [2 2])
                     (rectangle [1 1] [3 3])) ;=> false
(contains-rectangle? (rectangle [0 0] [1 1])
                     (rectangle [0 0] [1 1])) ;=> true
(contains-rectangle? (rectangle [0 0] [1 1])
                     (rectangle [1 1] [2 2])) ;=> false


;;; Maps
;;;;;;;;;;;

{"foo" 42, "bar" 666}
{"mehmeh" (+ 2 5)
 "rupatipor" "ropopo"}

;; indexed with get function.
(let [ages {"Juhana" 3
            "Ilmari" 42
            "King of All Cosmos" -6}]
  (get ages "King of All Cosmos"))
;; => -6
(comment
  (def book {:title "The City and the City"
             :authors [{:name "China Mieville", :birth-year 1972}]})

  (get book :title) ;; => "The City and the City"

  ;; keys act as lookup functions
  (:title book) ;; => "The City and the City"
  (def x 1)
  )

;; functions on collections
(count [ 1 2 3]) ;; => 3
(count {:name "China Miéville", :birth-year 1972}) ;; => 2
(count ":)") ;; => 2


;; authors and books


;;  Exercise 10

;; Write the function (title-length book) that counts the length of the book’s title.

(defn title-length [book]
  (count (:title book)))


;; Exercise 11

;; Write the function (author-count book) that returns the amount of authors that book
;; has.

(defn author-count [book]
  (count (:authors book)))


;;  Exercise 12

;; Write the function (multiple-authors? book) that returns `true` if book has multiple
;; authors, otherwise `false`

(defn multiple-authors? [book]
  (> (author-count book) 1))


;; Adding values to a Map or Vector

(assoc {:a 1} :b 2) ;; => {:a 1, :b 2}
(assoc {:a 1} :a 2) ;; => {:a 2}
(assoc [3 2 1] 1 "~o~") ;; => [3 "~o~" 1]

(let [original [1 2 3 4]
      new      (assoc original 2 "foo")]
  original)
;; => [1 2 3 4]


(defn add-author [book new-author]
  (let [authors (:authors book)]
    (assoc book :authors (conj authors new-author))))



(defn alive? [author]
  :-)




(defn element-lengths [collection]
  :-)



(defn second-elements [collection]
  :-)



(defn titles [books]
  :-)



(defn monotonic? [a-seq]
  :-)



(defn stars [n]
  :-)



(defn toggle [a-set elem]
  :-)



(defn contains-duplicates? [a-seq]
  :-)



(defn old-book->new-book [book]
  :-)



(defn has-author? [book author]
  :-)



(defn authors [books]
  :-)



(defn all-author-names [books]
  :-)



(defn author->string [author]
  :-)



(defn authors->string [authors]
  :-)



(defn book->string [book]
  :-)



(defn books->string [books]
  :-)



(defn books-by-author [author books]
  :-)



(defn author-by-name [name authors]
  :-)



(defn living-authors [authors]
  :-)



(defn has-a-living-author? [book]
  :-)



(defn books-by-living-authors [books]
  :-)



;; %________%
