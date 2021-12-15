from flask import (Flask, request, session, abort, flash)
from .aynch_calculator import (request_addition, get_last_calculation)
import os


def create_app(config=None):
    app = Flask(__name__, instance_relative_config=True)
    app.config.from_object(__name__)
    if config is not None:
        app.config.update(config)
    try:
        os.makedirs(app.instance_path)
    except OSError:
        pass

    @app.route(u"/calculation/addition/<user>", methods=['POST'])
    def start_addition(user):
        app.logger.info("startAddition")
        if request.form:
            request_addition(
                user, int(request.form['num1']), int(request.form['num2']))

        return "1"

    @app.route("/calculation/addition/<user>", methods=['GET'])
    def get_last_addition_result(user):
        app.logger.info(f"getLastAdditionResult for {user}")
        result = get_last_calculation(user)
        app.logger.info(f'result is {result}')

        return str(result)

    @app.route("/calculation/addition/", methods=['GET'])
    def heart_beat():
        app.logger.info("heartBeat")
        return "1"

    return app


if __name__ == '__main__':
    app = create_app()
    app.run()
