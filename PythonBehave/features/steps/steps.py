from behave import given, when, then


@given(u'flaskr is setup')
def flask_setup(context):
    assert context.client and context.db


@given(u'i login with "{username}" and "{password}"')
@when(u'i login with "{username}" and "{password}"')
def login(context, username, password):
    context.page = context.client.post('/login', data=dict(
        username=username,
        password=password
    ), follow_redirects=True)
    assert context.page


@given(u"at least {user_name} calculation service is running")
def at_least_some_calculation_service_is_running(context, user_name):
    assert context.client
    result = context.client.get("/calculation/addition/")

    assert result.data is not None and int(result.data) == 1


@given(u"user with name {user_name}")
def user_with_name(context, user_name):
    context.users.append(user_name)


@given(u"there are {n_results} more result(s)")
@then(u"there are {n_results} more result(s)")
def given_there_are_more_result(context, n_results):
    assert context.client
    result = context.client.get(f"/calculation/addition/{context.users[0]}")

    if int(n_results) > 0:
        assert result.data != b'None'
    else:
        assert result.data == b'None'


@ when(u"user {user_name} starts an addition of {num1} and {num2}")
def user_starts_an_addition(context, user_name,  num1, num2):
    assert context.client
    results = context.page = context.client.post(f'/calculation/addition/{user_name}', data=dict(
        num1=num1,
        num2=num2
    ))

    assert results.status_code == 200


@ when(u"user {user_name} downloads the result")
def user_downloads_result(context, user_name):
    assert context.client
    result = context.client.get(
        f"/calculation/addition/{context.users[max(len(context.users) -1, 0)]}")

    if result.data != b'None':
        context.results.append(int(result.data))


@ then(u"{n_results} result(s) of {answer} is downloaded")
def results_downloaded(context, n_results, answer):
    print(f"I have: {(context.results)} for {n_results}")
    assert len(context.results) == int(n_results)
    if int(n_results) > 0:
        assert int(answer) in context.results
