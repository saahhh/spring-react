//import logo from "./logo.svg";
import "./App.css";
import React, { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [data, setData] = useState([]);
  const [newUser, setNewUser] = useState({ username: "", email: "" });
  // const [data1, setData1] = useState("");
  // const [data2, setData2] = useState("");
  // fetchData라는 이름을 정의해서 try-catch문을 사용해 비동기 작업 중 발생하는 에러를 잡아내고 콘솔에 메세지를 출력하는 것
  // 간접적으로 호출한다는 말을 사용하기도 함
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.get("http://localhost:8080/api/user", {
          withCredentials: true,
        });
        setData(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []); // [] : 최초 1회 실행

  /*
  setNewUser((prevUser) => ({ ...prevUser, [name]: value }));
  여기서 작성한 name과 value는 input 값에 있는 name = 과 value를 의미
  name에서 username 은 http://localhost:8080/api/user에 json을 참조하여 작성한 username이므로 값을 변경하면 안됨
      <div>
        <label>회원 이름 : </label>
        <input
          type="text"
          name="username"
          value={newUser.username}
          onChange={handleInputChange}
        />
      </div>
  */

  // 데이터 작성한 내용으로 변경하는 함수
  const handleInputChange = (e) => {
    //(e) : event약자, 무언가를 실행하기 위해 ()가 아닌 (e)로 작성
    const { name, value } = e.target; // e 자리값 밑에 target (자리를 만들어준 것)
    setNewUser((prevUser) => ({ ...prevUser, [name]: value }));
  };
  // 전송하는 버튼 함수 추가
  // 데이터 보내줄 post 추가
  // await : 대기했다가 누르면 실행
  const handleAddUser = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/user",
        newUser,
        {
          withCredentials: true,
        }
      );
      // 변경된 데이터 값 저장해주기
      setData((prevUser) => [...prevUser, response.data]);
      // 데이터 저장되고 나서 빈 값으로 초기화 하길 원한다면 초기화도 진행해준다
      setNewUser({ username: "", email: "" });
    } catch (error) {
      console.error("데이터가 부적합합니다.", error);
    }
  };
  /*
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.get("http://localhost:8080/api/java", {
          withCredentials: true,
        });
        setData1(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.get("http://localhost:8080/api/react", {
          withCredentials: true,
        });
        setData2(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);
*/
  /*
  useEffect 안에서 직접 비동기 작업을 수행
  간단하게 catch문을 사용해서 에러를 처리하고 콘솔에 에러메세지를 출력
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/greet", { withCredentials: true })
      //response = res와 같은 의미이며, 안에 변수값은 정해진 변수값은 없지만 되도록이면 res나 response라는 이름을 사용하면 좋음
      .then((res) => {
        setData(res.data);
      })
      .catch((error) => {
        console.log("데이터 없음", error);
      });
  }, []);
  */
  return (
    <div>
      <h1>API 호출 확인</h1>
      <ul>
        {data.map((user) => (
          <li key={user.id}>
            {user.username} = {user.email}
          </li>
        ))}
      </ul>
      <h2>새로운 유저 저장</h2>
      <div>
        <label>회원 이름 : </label>
        <input
          type="text"
          name="username"
          value={newUser.username}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label>이메일 : </label>
        <input
          type="text"
          name="email"
          value={newUser.email}
          onChange={handleInputChange}
        />
      </div>
      <button onClick={handleAddUser}>유저 저장하기</button>
    </div>
  );
}

export default App;
