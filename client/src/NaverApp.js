import React from "react";
import NaverLogin from "react-naver-login";
//npm install react-naver-login

const NaverApp = () => {
  const clientId = "V6wtr1X2dEy08A2IRK_1";
  const NaverLoginSuccess = (res) => {
    console.log(res);
  };
  const NaverLoginFailure = (err) => {
    console.log(err);
  };
  // react-naver-login
  // render prop : 사용자가 버튼을 클릭한다는 것을 나타낸다는 추가 구문
  return (
    <div>
      <NaverLogin
        clientId={clientId}
        callbackUrl="http://localhost:3000/naverLogin"
        onSuccess={NaverLoginSuccess}
        onFailure={NaverLoginFailure}
        render={(props) => (
          <button onClick={props.onClick}>네이버로그인</button>
        )}
      />
    </div>
  );
};
export default NaverApp;
