//npm install react-kakao-login
//4f65f386c42b4acb10d1050997f9d690

import React from "react";
import KakaoLogin from "react-kakao-login";
import "bootstrap/dist/css/bootstrap.min.css";
import kakao_login from "./img/kakao_login.png";

const kakaoApp = () => {
  const KakaoLoginSuccess = (res) => {
    console.log(res);
  };
  const KakaoLoginFailure = (err) => {
    console.error(err);
  };
  return (
    <div>
      <KakaoLogin
        token="4f65f386c42b4acb10d1050997f9d690"
        onSuccess={KakaoLoginSuccess}
        onFailure={KakaoLoginFailure}
        //getProfile={true}

        render={(props) => (
          <button onClick={props.onClick}>
            <img src={kakao_login} />
          </button>
        )}
      />
    </div>
  );
};

export default kakaoApp;
