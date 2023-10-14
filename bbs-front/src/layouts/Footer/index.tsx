import React from 'react'
import './style.css';

//          component: 푸터 레이아웃            //
export default function Footer() {

    //      event handler: 깃헙 아이콘 버튼 클릭 이벤트 처리            //
    const onGithubIconButtonClickHandler = () => {
        window.open('https://github.com/guswls1414/');
    }

    //      event handler: 유튜브 아이콘 버튼 클릭 이벤트 처리            //
    const onYoutubeIconButtonClickHandler = () => {
        window.open('https://www.youtube.com/@hyun4189/');
    }

    //      render: 푸터 레이아웃 렌더링        //
  return (
    <div id='footer'>
        <div className='footer-container'>
            <div className='footer-top'>
                <div className='footer-logo-box'>
                    <div className='icon-box'>
                        <div className='icon logo-light-icon'></div>
                    </div>
                    <div className='footer-logo-text'>{'Hyuns Board'}</div>
                </div>
                <div className='footer-link-box'>
                    <div className='footer-email-link'>{'guswls1414@gmail.com'}</div>
                    <div className='icon-button' onClick={onGithubIconButtonClickHandler}>
                        <div className='icon github-icon'></div>
                    </div>
                    <div className='icon-button' onClick={onYoutubeIconButtonClickHandler}>
                        <div className='icon youtube-icon'></div>
                    </div>
                </div>
            </div>
            <div className='footer-bottom'>
                <div className='footer-copyright'>{'Copyright @ 2023 Hyun. All Rights Reserved.'}</div>
            </div>
        </div>
    </div>
  )
}
