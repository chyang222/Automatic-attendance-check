# 투명하게 얼굴에 착 붙어서 멀리서 보면 시트 붙인 줄도 모르겠어요 ㅋㅋ
# 한 20분 정도 붙이고 있었는데 어느 부위도 마르지 않고 끝까지 촉촉했어요
# 피부가 엄청 건조하고 예민한 편인데 진정에 진짜 좋은 것 같아요!!
# 붙일때 시원한 쿨링감이 느껴지면서 마무리감은 산뜻하고 수분 진정 다 채워줍니다
# 시트를 붙일때 코에 확 들어오는 향이 너무 좋아요😆

# !pip install googletrans==4.0.0-rc1
# !pip install transformers

# !pip install kiwipiepy

import streamlit as st
from streamlit_option_menu import option_menu
from streamlit_image_select import image_select
import face_recognition
from PIL import Image
import pandas as pd
import numpy as np
from tqdm import tqdm
import pydeck as pdk
import face_recognition as fr
from urllib.error import URLError
import cv2




st.set_page_config(layout="wide") # 꽉찬 화면구성
st.write('<style>div.row-widget.stRadio > div{flex-direction:row;}</style>', unsafe_allow_html=True)


def main() :
    #----------------------------------- Input ------------------------------------#
    tab1, tab2, tab3, tab4= st.tabs(["Home", "학생 정보", '인상 착의', '자동 출석'])

    with tab1:
        st.write('가천대학교 출석체크입니다.')
        st.write('핸드폰을 통해 이미지 업로드 후 출석체크를 도와드리겠습니다 :)')
        st.write('오늘도 좋은 하루 되세요.')
    
    # User_info
    with tab2:
        user_number = st.text_input(label = "학번", key='user_number')
        st.write('')
        user_name = st.text_input(label = "이름", key='user_name')
        st.write('')
        rating = st.radio(label = '수강과목', options = ['', '마케팅', '혁신전략', 'TPM', '데이터 엔지니어링', 'OR', '기술경영', '산종설계', '머신러닝'], index=0, key='rating')


    with tab3:
        cloth = []
        st.write('인상 착의(복수 선택 가능)')
        st.write('해당 착의에 맞춰 모델을 돌릴 예정입니다.')
        if st.checkbox('모자'):
            cloth.append('모자')
        if st.checkbox('마스크'):
            cloth.append('마스크')
        if st.checkbox('안경'):
            cloth.append('안경')


    #------------------------------ Recommen Process ------------------------------#
    phone_path = "c:\\Users\\user\\Desktop\\flask_server\\static"
    cyber_path = "c:\\Users\\user\\Desktop\\inova\\compare"

    with tab4:
        st.write("{}님의 출석을 도와드리겠습니다.".format(st.session_state.user_name))
        
        if st.button("자동 출석"):
            st.write('얼굴 유사도 분석 중..')
            try:
                # 이미지 로딩 및 얼굴 인코딩
                phone_img123 = fr.load_image_file(phone_path + '\\최성준.jpg')
                phone_img = cv2.cvtColor(phone_img123, cv2.COLOR_BGR2RGB)
                
                cyber_img123 = fr.load_image_file(cyber_path + '\\{}.JPG'.format(st.session_state.user_name))
                cyber_img = cv2.cvtColor(cyber_img123, cv2.COLOR_BGR2RGB)
                
                faceLoc = face_recognition.face_locations(phone_img)[0]
                encodeElon = face_recognition.face_encodings(phone_img)[0]
                cv2.rectangle(phone_img, (faceLoc[3], faceLoc[0]), (faceLoc[1], faceLoc[2]), (255, 0, 255), 2)

                faceLocTest = face_recognition.face_locations(cyber_img)[0]
                encodeTest = face_recognition.face_encodings(cyber_img)[0]
                cv2.rectangle(cyber_img, (faceLocTest[3], faceLocTest[0]), (faceLocTest[1], faceLocTest[2]), (255, 0, 255), 2)

                # 얼굴 유사도 분석
                results = face_recognition.compare_faces([encodeElon], encodeTest)
                faceDis = face_recognition.face_distance([encodeElon], encodeTest)

                if faceDis[0] > 0.3:
                    total = True
                else:
                    total = False

                faceDis[0] = round(faceDis[0], 3)
                cv2.putText(phone_img123, f'{total} {1 - faceDis[0]}', (70, 70), cv2.FONT_HERSHEY_COMPLEX, 3, (30, 30, 255), 3)

                # 이미지 이어 붙이기
                phone_img123 = cv2.resize(phone_img123, (500, 500))
                cyber_img123 = cv2.resize(cyber_img123, (500, 500))

                new_image = Image.new("RGB", (cyber_img123.shape[1] + phone_img123.shape[1], max(cyber_img123.shape[0], phone_img123.shape[0])))
                new_image.paste(Image.fromarray(cyber_img123), (0, 0))
                new_image.paste(Image.fromarray(phone_img123), (cyber_img123.shape[1], 0))

                st.image(new_image)

                cv2.waitKey(0)
                if total:
                    st.write("자동 출석이 완료하였습니다.")
                else:
                    st.write("얼굴이 유사하지 않아 수동출석해주시기 바랍니다.")
            
            except:
                st.write("해당 학생의 이미지가 없습니다.")
                st.write("이미지를 업로드 해주시기 바랍니다.")


    
if __name__ == '__main__' :
    main()
