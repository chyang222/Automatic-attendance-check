from flask import Flask, request, jsonify
import os
from werkzeug.utils import secure_filename
from PIL import Image
import numpy as np
import cv2

app_flask = Flask(__name__)

UPLOAD_FOLDER = 'c:\\Users\\user\\Desktop\\flask_server\\static'
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg', 'gif'}

app_flask.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


@app_flask.route('/upload', methods=['GET', 'POST'])
def upload_image():
    try:
        if 'image' not in request.files:
            return jsonify({'error': '이미지 부분이 없습니다.'}), 400

        file = request.files['image']

        if file.filename == '':
            return jsonify({'error': '선택된 파일이 없습니다.'}), 400

        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            filepath = os.path.join(app_flask.config['UPLOAD_FOLDER'], filename)

            # Open the image using OpenCV for cropping
            image_cv2 = cv2.imdecode(np.frombuffer(file.read(), np.uint8), cv2.IMREAD_COLOR)

            # Rotate the image by 270 degrees
            rotated_image = cv2.rotate(image_cv2, cv2.ROTATE_90_CLOCKWISE)

            # Crop the image to remove black borders
            cropped_image = crop_black_borders(rotated_image)

            # Resize the image while maintaining the aspect ratio
            resized_image = resize_image(cropped_image, target_width=800)

            # Save the resized and cropped image to the file system
            cv2.imwrite(filepath, resized_image)

            return jsonify({
                'filename': filename,
                'message': '업로드 성공'
            })
        else:
            return jsonify({'error': '잘못된 파일 유형입니다.'}), 400

    except Exception as e:
        return jsonify({'error': str(e)}), 500


def crop_black_borders(image):
    # Convert the image to grayscale
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Find the non-zero region
    coords = cv2.findNonZero(gray)

    # Find the bounding box of the non-zero region
    x, y, w, h = cv2.boundingRect(coords)

    # Crop the image
    cropped = image[y:y + h, x:x + w]

    return cropped


def resize_image(image, target_width):
    # Calculate the ratio of the target width to the current width
    ratio = target_width / image.shape[1]

    # Calculate the new height to maintain the aspect ratio
    target_height = int(image.shape[0] * ratio)

    # Resize the image
    resized_image = cv2.resize(image, (target_width, target_height))

    return resized_image


if __name__ == '__main__':
    app_flask.run(host='192.9.67.107', port=5000, debug=True)
