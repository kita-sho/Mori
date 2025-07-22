ANT	= env LC_ALL=ja_JP.UTF-8 ant
CWD	= $(shell basename `pwd`)
SOURCES	= $(shell find . -name "*.java")
STYLE_YAML	= clang-format-for-java.yaml
STYLE_CONF	= _clang-format

APP = Mori

.PHONY: all clean open test install doc wipe zip format app

all:
	$(ANT) all

clean:
	$(ANT) clean

open:
	$(ANT) open

test:
	$(ANT) test

install:
	$(ANT) install

doc:
	$(ANT) doc

wipe: clean
	@find . -name ".DS_Store" -exec rm {} ";" -exec echo rm -f {} ";"
	(cd ../ ; rm -f ./$(APP).zip)

#zip:
#	$(ANT) zip

zip: wipe
	@find . -exec touch -t `date "+%Y%m%d%H%M"` {} \; ; xattr -cr .
	(cd ../ ; zip -r ./$(APP).zip ./$(CWD)/ --exclude='*/.svn/*')

format:
	@rm -f $(STYLE_CONF) ; ln -s $(STYLE_YAML) $(STYLE_CONF)
	for each in $(SOURCES) ; do echo ---[$${each}]--- ; clang-format -style=file $${each} ; echo ; done
	@rm -f $(STYLE_CONF)

app: install
	@xattr -cr ./$(APP).app
	open ./$(APP).app