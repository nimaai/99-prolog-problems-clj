% P1: find last element of a list
my_last(X, [X]) :- !.
my_last(X, [_ | R]) :- my_last(X, R).

% P2: Find the last but one element of a list.
my_butlast(X, [X | [_]]) :- !.
my_butlast(X, [_ | R]) :- my_butlast(X, R).

% P3: Find the K'th element of a list.
my_element_at(_, [], _) :- fail.
my_element_at(X, [X | _], 1).
my_element_at(X, [_ | R], K1) :- K2 is K1 - 1, my_element_at(X, R, K2).

% P4: Find the number of elements of a list.
my_length([], 0).
my_length([_ | R], N) :- my_length(R, N2), N is N2 + 1.

% P5: Reverse a list.
my_reverse([], []).
my_reverse([H | T], R) :- my_reverse(T, RT), append(RT, [H], R).

% P06 (*) Find out whether a list is a palindrome.
palindrome(L) :- reverse(L, L).

% P07 (**) Flatten a nested list structure.
my_flatten(X,[X]) :- \+ is_list(X).
my_flatten([],[]).
my_flatten([X|Xs],Zs) :- my_flatten(X,Y), my_flatten(Xs,Ys), append(Y,Ys,Zs).

% P08 (**) Eliminate consecutive duplicates of list elements.
% If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.

% Example:
% ?- compress([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
% X = [a,b,c,a,d,e]
compress([],[]).
compress([X],[X]).
compress([X,X|Xs],Zs) :- compress([X|Xs],Zs).
compress([X,Y|Ys],[X|Zs]) :- X \= Y, compress([Y|Ys],Zs).

% P09 (**) Pack consecutive duplicates of list elements into sublists.
% If a list contains repeated elements they should be placed in separate sublists.
% Example:
% ?- pack([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
% X = [[a,a,a,a],[b],[c,c],[a,a],[d],[e,e,e,e]]
pack([],[]).
pack([X|Xs],[Z|Zs]) :- transfer(X,Xs,Ys,Z), pack(Ys,Zs).

% transfer(X,Xs,Ys,Z) Ys is the list that remains from the list Xs
%    when all leading copies of X are removed and transfered to Z
transfer(X,[],[],[X]).
transfer(X,[Y|Ys],[Y|Ys],[X]) :- X \= Y.
transfer(X,[X|Xs],Ys,[X|Zs]) :- transfer(X,Xs,Ys,Zs).

% P10 (*):  Run-length encoding of a list

% encode(L1,L2) :- the list L2 is obtained from the list L1 by run-length
%    encoding. Consecutive duplicates of elements are encoded as terms [N,E],
%    where N is the number of duplicates of the element E.
%    (list,list) (+,?)
encode(X, R) :- pack(X, P), transform(P, R).

transform([],[]).
transform([[X|Xs]|Ys],[[N,X]|Zs]) :- length([X|Xs],N), transform(Ys,Zs).

% P11 (*) Modified run-length encoding.
% Modify the result of problem P10 in such a way that if an element has no duplicates
% it is simply copied into the result list. Only elements with duplicates are transferred as [N,E] terms.
encode_modified(L1,L2) :- encode(L1,L), strip(L,L2).

strip([],[]).
strip([[1,X]|Ys],[X|Zs]) :- strip(Ys,Zs).
strip([[N,X]|Ys],[[N,X]|Zs]) :- N > 1, strip(Ys,Zs).

% P12 (**) Decode a run-length encoded list.
% Given a run-length code list generated as specified in problem P11. Construct its uncompressed version.
decode([],[]).
decode([X|Ys],[X|Zs]) :- \+ is_list(X), decode(Ys,Zs).
decode([[1,X]|Ys],[X|Zs]) :- decode(Ys,Zs).
decode([[N,X]|Ys],[X|Zs]) :- N > 1, N1 is N - 1, decode([[N1,X]|Ys],Zs).

% P13 (**): Run-length encoding of a list (direct solution) 

% encode_direct(L1,L2) :- the list L2 is obtained from the list L1 by 
%    run-length encoding. Consecutive duplicates of elements are encoded 
%    as terms [N,E], where N is the number of duplicates of the element E.
%    However, if N equals 1 then the element is simply copied into the 
%    output list.
%    (list,list) (+,?)

encode_direct([],[]).
encode_direct([X|Xs],[Z|Zs]) :- count(X,Xs,Ys,1,Z), encode_direct(Ys,Zs).

% count(X,Xs,Ys,K,T) Ys is the list that remains from the list Xs
%    when all leading copies of X are removed. T is the term [N,X],
%    where N is K plus the number of X's that can be removed from Xs.
%    In the case of N=1, T is X, instead of the term [1,X].

count(X,[],[],1,X).
count(X,[],[],N,[N,X]) :- N > 1.
count(X,[Y|Ys],[Y|Ys],1,X) :- X \= Y.
count(X,[Y|Ys],[Y|Ys],N,[N,X]) :- N > 1, X \= Y.
count(X,[X|Xs],Ys,K,T) :- K1 is K + 1, count(X,Xs,Ys,K1,T).

% P14 (*) Duplicate the elements of a list.
% Example:
% ?- dupli([a,b,c,c,d],X).
% X = [a,a,b,b,c,c,c,c,d,d]
dupli([],[]).
dupli([X|Y],[X,X|Z]) :- dupli(Y,Z).

% P15 (**): Duplicate the elements of a list agiven number of times

% dupli(L1,N,L2) :- L2 is obtained from L1 by duplicating all elements
%    N times.
%    (list,integer,list) (?,+,?)

dupli(L1,N,L2) :- dupli(L1,N,L2,N).

% dupli(L1,N,L2,K) :- L2 is obtained from L1 by duplicating its leading
%    element K times, all other elements N times.
%    (list,integer,list,integer) (?,+,?,+)

dupli([],_,[],_).
dupli([_|Xs],N,Ys,0) :- dupli(Xs,N,Ys,N).
dupli([X|Xs],N,[X|Ys],K) :- K > 0, K1 is K - 1, dupli([X|Xs],N,Ys,K1).

% P16 (**):  Drop every N'th element from a list

% drop(L1,N,L2) :- L2 is obtained from L1 by dropping every N'th element.
%    (list,integer,list) (?,+,?)

drop(L1,N,L2) :- drop(L1,N,L2,N).

% drop(L1,N,L2,K) :- L2 is obtained from L1 by first copying K-1 elements
%    and then dropping an element and, from then on, dropping every
%    N'th element.
%    (list,integer,list,integer) (?,+,?,+)

drop([],_,[],_).
drop([_|Xs],N,Ys,1) :- drop(Xs,N,Ys,N).
drop([X|Xs],N,[X|Ys],K) :- K > 1, K1 is K - 1, drop(Xs,N,Ys,K1).

% P17 (*) Split a list into two parts; the length of the first part is given.
% Do not use any predefined predicates.

% Example:
% ?- split([a,b,c,d,e,f,g,h,i,k],3,L1,L2).
% L1 = [a,b,c]
% L2 = [d,e,f,g,h,i,k]

split(L1, 0, [], L1).
split([X | XS], N, [X | YS], ZS) :-
  N > 0,
  N1 is N - 1,
  split(XS, N1, YS, ZS). 






