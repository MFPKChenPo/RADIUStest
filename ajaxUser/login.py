from flask import Flask, request, render_template
import os

app = Flask(__name__)

@app.route('/')
def hello():
    return 'hello world'


@app.route('/login', methods=['GET','POST'])
def handle():
    if request.method == 'POST': 
        account = request.values['user_name']
        password = request.values['user_password']
        cmdExec = "curl -X POST http://localhost:8181/testRadius/ChenPoTest -u onos:rocks -d " + "'user=" +\
             account + "&" +"pass=" + password + "'"
        f = os.popen(cmdExec)
        result = f.read()

        return result

    return render_template('index.html')

if __name__ == '__main__':
    app.debug = True
    app.run()