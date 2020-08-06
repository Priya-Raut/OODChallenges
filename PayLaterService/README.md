# PayLaterService

## Usecases:
There are various use cases our service is intended to fulfil -

● allow merchants to be onboarded with the amount of discounts they offer

● allow merchants to change the discount they offer

● allow users to be onboarded (name, email-id and credit-limit)

● allow a user to carry out a transaction of some amount with a merchant.

● allow a user to pay back their dues (full or partial)

● Reporting:

○ how much discount we received from a merchant till date

○ dues for a user so far

○ which users have reached their credit limit

○ total dues from all users together

## Example Input from commandline

new user u1 u1@email.in 1000 # name, email, credit-limit

new merchant m1 2% # name, discount-percentage

new txn u1 m2 400 # user, merchant, txn-amount

update merchant m1 1% # merchant, new-discount-rate

payback u1 300 # user, payback-amount

report discount m1

report dues u1

report users-at-credit-limit

report total-dues
