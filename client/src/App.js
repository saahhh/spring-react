import KakaoApp from "./KakaoApp";
import GoogleApp from "./GoogleApp";
import NaverApp from "./NaverApp";
import NaverLoginButton from "./NaverLoginButton";

const App = () => {
  return (
    <div>
      <GoogleApp />
      <NaverApp />
      <KakaoApp />
      <NaverLoginButton />
    </div>
  );
};

export default App;
