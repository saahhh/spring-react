import React, { useState } from "react";
import axios from "axios";

function AddProduct({ onAddProduct }) {
  const [newProduct, setNewProduct] = useState({ name: "", price: 0 });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewProduct((prevProduct) => ({ ...prevProduct, [name]: value }));
  };
  //async : 비동기데이터 시작할거야 (예고)
  //await : 취합한 데이터 보냄
  const handleAddProduct = async () => {
    try {
      //input값 보내줄 post 작성
      const response = await axios.post(
        "http://localhost:8081/api/add",
        newProduct,
        {
          withCredentials: true,
        }
      );
      //변경된 데이터 값 저장
      onAddProduct((prevProducts) => [...prevProducts, response.data]);
      //onAddProduct(response.data);
      //저장 후 값 초기화
      setNewProduct({ name: "", price: 0 });
    } catch (error) {
      console.error("데이터가 부적합합니다.", error);
    }
  };
  return (
    <div>
      <h3>상품 추가</h3>
      <div>
        <label>상품명 : </label>
        <input
          type="text"
          name="name"
          value={newProduct.name}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label>상품 가격 : </label>
        <input
          type="number"
          name="price"
          value={newProduct.price}
          onChange={handleInputChange}
        />
      </div>
      <button onClick={handleAddProduct}>상품 추가하기</button>
    </div>
  );
}
export default AddProduct;
