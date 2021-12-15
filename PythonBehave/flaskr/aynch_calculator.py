users_calculation = {}


def request_addition(user, num1, num2):
    users_calcs = users_calculation.get(user)

    if (users_calcs is None):
        users_calcs = list()

    users_calcs.append(num1+num2)
    users_calculation[user] = users_calcs


def get_last_calculation(user):
    users_calcs = users_calculation.get(user)
    results = None

    if (users_calcs is not None and len(users_calcs) > 0):
        results = users_calcs.pop()

    return results
