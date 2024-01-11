import React, { useEffect } from "react";

const KakaoMap = () => {
  useEffect(() => {
    //카카오맵 api를 가지고오는 스크립트
    const script = document.createElement("script");
    script.async = true;
    script.src =
      "http://dapi.kakao.com/v2/maps/sdk.js?89730aca4ca56bd725e48019977366cc&autolaod=false";

    document.head.appendChild(script);

    //스크립트 추가했으면 실행
    script.onload = () => {
      window.kakao.maps.load(() => {
        const container = document.getElementById("map");
        const options = {
          //초기에 지도 중심 좌표
          center: new window.kakao.maps.LatLng(37.5665, 126.978),
          level: 3,
        };

        const map = new window.kakao.maps.Map(container, options);

        //마커 추가
        const markerPosition = new window.kakao.maps.LatLng(37.5665, 126.978);
        const marker = new window.kakao.maps.Marker({
          position: markerPosition,
        });
        marker.setMap(map);
      });
    };
  }, []);
  return <div id="map"></div>;
};

export default KakaoMap;


import React, { useEffect } from 'react';

const KakaoMap = () => {
  useEffect(() => {
    //카카오맵 api를 가지고오는 스크립트
    const script = document.createElement('script');
    script.async = true;
    script.src =
      'https://dapi.kakao.com/v2/maps/sdk.js?자바스크립트API키&autoload=false';
    document.head.appendChild(script);

    //스크립트 추가했으면 실행
    script.onload = () => {
      window.kakao.maps.load(() => {
        const container = document.getElementById('map');
        const options = {
          //초기에 지도 중심 좌표
          center: new window.kakao.maps.LatLng(37.5665, 126, 978),
          level: 3,
        };

        const map = new window.kakao.maps.Map(container, options);

        // 마커 추가
        const markerPosition = new window.kakao.maps.LatLng(37.5665, 126.978);
        const marker = new window.kakao.maps.Marker({
          position: markerPosition,
        });
        marker.setMap(map);
      });
    };
  }, []);
  return <div id="map"></div>;
};
export default KakaoMap;