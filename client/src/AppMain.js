import React, { useEffect, useState } from "react";
import axios from "axios";
//react-router-dom
//import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
//Switch -> Routes
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import AddProduct from "./AddProduct";

function AppMain() {
  //data가 아닌 products로 적을 예정
  const [products, setProducts] = useState([]);
  const [newProduct, setNewProduct] = useState({ name: "", price: 0 });

  //get해주는 axios api작성
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.get("http://localhost:8080/api/product/item", {
          withCredentials: true,
        });
        setProducts(res.data);
      } catch (error) {
        console.log("데이터를 불러오지 못했습니다.", error);
      }
    };
    fetchData();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewProduct((prevProduct) => ({ ...prevProduct, [name]: value }));
  };

  const handleAddProduct = async () => {
    try {
      //input값 보내줄 post 작성
      const response = await axios.post(
        "http://localhost:8080/api/add",
        newProduct,
        {
          withCredentials: true,
        }
      );
      //변경된 데이터 값 저장
      setProducts((prevProduct) => [...prevProduct, response.data]);
      //저장 후 값 초기화
      setNewProduct({ name: "", price: 0 });
    } catch (error) {
      console.error("데이터가 부적합합니다.", error);
    }
  };

  return (
    <Router>
      <div>
        <h2>상품 리스트</h2>
        <ul>
          {products.map((product) => (
            <li key={product.id}>
              {product.name} - {product.price}원
            </li>
          ))}
          <li>
            <Link to="/add">상품추가하기</Link>
          </li>
        </ul>

        <Routes>
          <Route
            path="/add"
            element={<AddProduct onAddProduct={handleAddProduct} />}
          />
          {/*상품리스트추가 Js만들어서 element 추가할 예정*/}

          <Route path="/item" element={<h3>상품리스트</h3>} />
        </Routes>
      </div>
    </Router>
  );
}

export default AppMain;
