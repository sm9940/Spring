package com.example.hotel.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class PagingUtil {
    private int dataCount; // 총 게시물의 갯수
    private int numPerPage; //페이지당 보여줄 데이터의 갯수
    private int totalPage; //페이지의 전체 갯수
    private int currentPage = 1; //현재페이지
    private int start; //rownum의 시작값
    private  int end; // rownum의 끝값

    public void resetPaging(int dataCount, int numPerPage){
        this.dataCount = dataCount;
        this.numPerPage = numPerPage;
        this.totalPage= getPageCount();

        //현재 페이지가 totalPage보다 큰 경우는 있을수 없으므로..
        if(this.currentPage> totalPage) this.currentPage = this.totalPage;

        this.start = (this.currentPage-1) * numPerPage +1;
        this.end = this.currentPage * numPerPage;
    }

    //전체 페이지 갯수 구하는 메소드
    public int getPageCount(){
        int pageCount = 0;
        pageCount = dataCount/numPerPage;
        if(dataCount % numPerPage != 0) pageCount++;

        return pageCount;
    }

    //페이징 버튼을 만들어주는 메소드

    public String pageIndexList(String listUrl){
        //문자열 데이터를 자주 추가하거나 삭제할때 메모리 낭비 방지를 위해 사용
        StringBuffer sb = new StringBuffer();

        int numPerBlock = 5; //이전과 다음 사이의 숫자를 몇개로 표시
        int currentPageSetup; //
        int page;

        if(currentPage == 0 || totalPage == 0){
            return "";
        }
        if(listUrl.indexOf("?") != -1){ //쿼리스트링이 있을때(검색어가 있을때)
            listUrl += "&";
        } else { //쿼리스트링이 없을때(검색어가 없을때)
            listUrl += "?";
        }

        //1.  이전버튼 만들기
        currentPageSetup = (currentPage/ numPerBlock) * numPerBlock;

        if(currentPage % numPerBlock ==0){
            currentPageSetup = currentPageSetup - numPerBlock;
        }

        if(totalPage > numPerPage && currentPageSetup > 0){
            sb.append("<li class=\"page-item\">" +
                    "       <a class=\"page-link\" href=\"" + listUrl + "pageNum=" + currentPageSetup + "\" aria-label=\"Previous\">" +
                    "            <span aria-hidden=\"true\">이전</span>" +
                    "       </a>" +
                    " </li>");
        }

        //2. 그냥 페이지(6 7 8 9) 이동 버튼 만들기
        page= currentPageSetup +1;
        while (page <= totalPage && page <= (currentPageSetup + numPerBlock)){
            if(page == currentPage){
                sb.append("<li class=\"page-item active\"><a class=\"page-link\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a></li>");
            }   else {
                sb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a></li>");
            }
            page++;
        }

        //3. 다음 버튼 만들기
        if(totalPage - currentPageSetup> numPerBlock){
            //pageNum: 6,11,16,21 ...
            sb.append("<li class=\"page-item\">" +
                    "     <a class=\"page-link\" href=\"" + listUrl + "pageNum=" + (currentPageSetup + numPerBlock + 1) + "\" aria-label=\"Next\">" +
                    "         <span aria-hidden=\"true\">다음</span>" +
                    "     </a>" +
                    " </li>");
        }
        return sb.toString();
    }
}
