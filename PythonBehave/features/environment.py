from flaskr import app


def before_feature(context, feature):
    flask_app = app.create_app()
    flask_app.testing = True
    context.client = flask_app.test_client()


def before_scenario(context, scenario):
    context.users = list()
    context.results = list()
